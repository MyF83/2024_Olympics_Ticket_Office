import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, mergeMap, tap } from 'rxjs/operators';
import { Ticket } from '../interfaces/ticket.interface';

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  private apiUrl = 'http://localhost:8080/api/tickets';
  
  constructor(private http: HttpClient) { }
  
  /**
   * Register a new ticket with the backend
   * @param ticketData The ticket data to register
   * @returns Observable with the registered ticket data including QR code
   */
  registerTicket(ticketData: any): Observable<any> {
    console.log('===== DEBUG: Registering ticket with backend =====', ticketData);
    
    // Check if we already have user_id in the ticket data
    if (!ticketData.user_id) {
      console.log('No user_id in ticket data, trying to find it');
      
      // Try to get user_id from userData in localStorage
      const userData = localStorage.getItem('userData');
      if (userData) {
        try {
          const user = JSON.parse(userData);
          if (user.id || user.user_id) {
            ticketData.user_id = user.id || user.user_id;
            console.log('Added user_id to ticket data:', ticketData.user_id);
          }
        } catch (error) {
          console.error('Error parsing userData:', error);
        }
      } else {
        console.warn('No userData in localStorage');
      }
      
      // If still no user_id, try to get from session token
      if (!ticketData.user_id) {
        const token = sessionStorage.getItem('token');
        if (token) {
          console.log('Found token, will try to get current user');
          // We'll handle this in the pipe below
        } else {
          console.warn('No token found in sessionStorage');
        }
      }
    } else {
      console.log('Ticket data already has user_id:', ticketData.user_id);
    }
    
    // First register the ticket to get QR code
    return this.http.post<any>(`${this.apiUrl}/register`, ticketData).pipe(
      mergeMap(response => {
        if (response && !response.error) {
          console.log('Ticket registered successfully, QR code created:', response);
          
          // We need to make sure we have a user_id
          if (!ticketData.user_id) {
            // Try to get current user
            return this.http.get<any>('/api/user/current').pipe(
              mergeMap(user => {
                if (user && user.user_id) {
                  console.log('Got current user:', user);
                  ticketData.user_id = user.user_id;
                  
                  // Save the complete ticket to the database
                  const completeTicket = {
                    ...ticketData,
                    qrCodeBase64: response.qrCodeBase64,
                    keyAssembly: response.keyAssembly,
                    user_id: user.user_id
                  };
                  
                  console.log('Saving complete ticket with user_id to backend:', completeTicket);
                  return this.http.post<any>(`${this.apiUrl}/persist`, completeTicket).pipe(
                    map(saveResponse => {
                      console.log('Ticket saved to database:', saveResponse);
                      return response; // Return original response with QR code
                    }),
                    catchError(error => {
                      console.error('Error saving ticket to database:', error);
                      return of(response); // Return original response if save fails
                    })
                  );
                }
                return of(response); // Return original response if no user found
              }),
              catchError(error => {
                console.error('Error getting current user:', error);
                return of(response); // Return original response if user fetch fails
              })
            );
          } else {
            // We already have user_id, save the complete ticket
            const completeTicket = {
              ...ticketData,
              ticketNumber: response.ticketNumber,
              fileName: response.fileName,
              qrCodePath: response.qrCodePath,
              qrCodeBase64: response.qrCodeBase64,
              keyAssembly: response.keyAssembly,
              user_id: ticketData.user_id
            };
            
            console.log('Saving complete ticket to backend:', completeTicket);
            return this.http.post<any>(`${this.apiUrl}/persist`, completeTicket).pipe(
              map(saveResponse => {
                console.log('Ticket saved to database:', saveResponse);
                
                // Store complete ticket data in local storage
                this.storeTicket({
                  ...completeTicket,
                  id: saveResponse.ticketId
                });
                
                // Return combined response
                return {
                  ...response,
                  persistId: saveResponse.ticketId
                };
              }),
              catchError(error => {
                console.error('Error saving ticket to database:', error);
                return of(response); // Return original response if save fails
              })
            );
          }
        } else {
          console.error('Error in ticket registration response:', response?.error);
          return of({ error: response?.error || 'Failed to register ticket' });
        }
      }),
      catchError(error => {
        console.error('Error registering ticket:', error);
        return of({ error: 'Failed to register ticket' });
      })
    );
  }
  
  /**
   * Get QR code as Base64 for a ticket
   * @param ticketNumber The ticket number
   * @returns Observable with the QR code data
   */
  getQRCodeBase64(ticketNumber: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/qrcode/base64/${ticketNumber}`).pipe(
      catchError(error => {
        console.error('Error getting QR code:', error);
        return of({ error: 'Failed to get QR code' });
      })
    );
  }
  
  /**
   * Generate a ticket number based on user's first and last name
   * @param firstName User's first name
   * @param lastName User's last name
   * @returns A ticket number
   */
  generateTicketNumber(firstName: string, lastName: string): string {
    // Get initials
    const firstInitial = firstName.charAt(0).toUpperCase();
    const lastInitial = lastName.charAt(0).toUpperCase();
    
    // Get current date in YYYYMMDD format
    const now = new Date();
    const year = now.getFullYear();
    const month = (now.getMonth() + 1).toString().padStart(2, '0');
    const day = now.getDate().toString().padStart(2, '0');
    const date = `${year}${month}${day}`;
    
    // Generate 6-digit random number
    const randomNum = Math.floor(Math.random() * 1000000).toString().padStart(6, '0');
    
    // Combine all parts
    return `${firstInitial}${lastInitial}-${date}-${randomNum}`;
  }
  
  /**
   * Store ticket data in local storage
   * @param ticket The ticket to store
   */
  storeTicket(ticket: Ticket): void {
    console.log('===== DEBUG: Storing ticket in localStorage =====', ticket);
    localStorage.setItem('purchasedTicket', JSON.stringify(ticket));
    
    // Also store in a tickets array if it doesn't exist yet
    try {
      let storedTickets: Ticket[] = [];
      const existingTicketsString = localStorage.getItem('userTickets');
      
      if (existingTicketsString) {
        storedTickets = JSON.parse(existingTicketsString);
      }
      
      // Check if this ticket already exists in the array by ticket number
      const existingTicketIndex = storedTickets.findIndex(t => 
        t.ticketNumber === ticket.ticketNumber || 
        (t.ticket_id === ticket.ticket_id)
      );
      
      if (existingTicketIndex >= 0) {
        // Update existing ticket
        storedTickets[existingTicketIndex] = ticket;
      } else {
        // Add new ticket
        storedTickets.push(ticket);
      }
      
      localStorage.setItem('userTickets', JSON.stringify(storedTickets));
      console.log('Updated userTickets in localStorage, now has', storedTickets.length, 'tickets');
    } catch (error) {
      console.error('Error updating userTickets in localStorage:', error);
    }
  }
  
  /**
   * Retrieve ticket data from local storage
   * @returns The stored ticket or null
   */
  getStoredTicket(): Ticket | null {
    const ticketData = localStorage.getItem('purchasedTicket');
    return ticketData ? JSON.parse(ticketData) : null;
  }
  
  /**
   * Clear stored ticket data from local storage
   */
  clearStoredTicket(): void {
    localStorage.removeItem('purchasedTicket');
  }
  
  /**
   * Get all tickets for the current logged-in user
   * @returns Observable with the user's tickets
   */
  getUserTickets(): Observable<Ticket[]> {
    console.log('Getting user tickets from backend');
    
    // Get user info from localStorage
    const userData = localStorage.getItem('userData');
    if (!userData) {
      console.error('No user data found in localStorage');
      
      // Try to get from session storage or token
      const token = sessionStorage.getItem('token');
      if (token) {
        // We have a token but no userData, try to get current user
        console.log('Token found but no userData, trying to get current user');
        return this.http.get<any>('/api/user/current').pipe(
          mergeMap(user => {
            if (user && user.user_id) {
              console.log('Current user retrieved:', user);
              
              // Save user data for future use
              localStorage.setItem('userData', JSON.stringify({
                firstName: user.firstname,
                lastName: user.lastname,
                user_id: user.user_id,
                id: user.user_id,
                username: user.username,
                email: user.email
              }));
              
              console.log('Fetching tickets for user ID:', user.user_id);
              return this.http.get<Ticket[]>(`${this.apiUrl}/user/${user.user_id}`).pipe(
                catchError(error => {
                  console.error('Error fetching user tickets:', error);
                  return of([]);
                })
              );
            }
            return of([]);
          }),
          catchError(error => {
            console.error('Error getting current user:', error);
            return of([]);
          })
        );
      }
      
      return of([]);
    }
    
    try {
      const user = JSON.parse(userData);
      // Check for either id or user_id
      const userId = user.id || user.user_id;
      
      if (!userId) {
        console.error('No user ID found in user data');
        return of([]);
      }
      
      console.log('Fetching tickets for user ID:', userId);
      return this.http.get<Ticket[]>(`${this.apiUrl}/user/${userId}`).pipe(
        catchError(error => {
          console.error('Error fetching user tickets:', error);
          return of([]);
        })
      );
    } catch (error) {
      console.error('Error parsing user data:', error);
      return of([]);
    }
  }
}