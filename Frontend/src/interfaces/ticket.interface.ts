export interface Ticket {
    ticket_id?: number;
    ticketNumber: string;
    date?: Date;
    purchaseDate?: Date;  // Added for template compatibility
    keyAssembly?: string;
    fileName?: string;
    qrCodePath?: string;
    qrCodeBase64?: string;
    
    // Frontend specific fields for display
    eventTitle?: string;
    offerName?: string;
    offerDiscountRate?: number;
    nbSpectators?: number;
    priceClass?: string;
    eventDate?: Date;
    eventLocation?: string;
    eventImageUrl?: string;
    totalPrice?: number;
}