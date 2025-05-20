import { Routes } from '@angular/router';
import { HomeComponent } from './components/games/games.component';
import { OffersComponent } from './components/offers/offers.component';
import { InfosComponent } from './components/infos/infos.component';
import { AccountComponent } from './components/account/account.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/games',
    pathMatch: 'full',
    title: 'Games',
  },
  {
    path: 'games',
    pathMatch: 'full',
    component: HomeComponent,
    title: 'Games',
  },
  {
    path: 'offers',
    pathMatch: 'full',
    component: OffersComponent,
    title: 'Offers',
  },
  {
    path: 'infos',
    pathMatch: 'full',
    component: InfosComponent,
    title: 'Informations',
  },
  {
    path: 'account',
    pathMatch: 'full',
    component: AccountComponent,
    title: 'Account',
  },
  {
    path: '**',
    pathMatch: 'full',
    component: PageNotFoundComponent,
    title: 'Page Not Found',
  },
];

export default routes;

