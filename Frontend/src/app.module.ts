import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { SafeUrlPipe } from './safe-url.pipe'; 


// Update the path below if the folder name should be 'interceptor' (lowercase) or adjust as needed
//import { JwtInterceptor } from './interceptor/jwt-interceptor';

/*
@NgModule({
  declarations: [
    // ...existing components...
  ],
  imports: [
    // ...existing modules...
    MatSelectModule,
    MatOptionModule
  ],
 providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }
  ]
})
export class AppModule { }*/