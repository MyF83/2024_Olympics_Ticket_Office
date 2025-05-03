
import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app.config';
import { AppComponent } from './app.component';

bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));

/* before
  import { bootstrapApplication } from '@angular/platform-browser';
  import { provideHttpClient } from '@angular/common/http';
  import { HomeComponent } from './components/games/games.component';
  
  bootstrapApplication(HomeComponent, {
    providers: [provideHttpClient()]
  }).catch(err => console.error(err));  */
