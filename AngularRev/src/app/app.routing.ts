import {ModuleWithProviders} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {StocksComponent} from './stocks.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {StockFormComponent} from './stock-form/stock-form.component';

const appRoutes: Routes = [
    {
        path: '',
        component: DashboardComponent
    },
    {
        path: 'stocks',
        component: StocksComponent
    },
    {
        path: 'stockform',
        component: StockFormComponent
    }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
