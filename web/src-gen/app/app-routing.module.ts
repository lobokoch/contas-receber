/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:26:04.789
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

// Angular
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

// Kerubin - BEGIN
import { ConfirmAccountComponent } from './account/confirmaccount/confirmaccount.component';
import { NewAccountComponent } from './account/newaccount/newaccount.component';
import { ConfigNewAccountComponent } from './account/confignewaccount/confignewaccount.component';
import { LoginComponent } from './security/login/login.component';
// Kerubin - END

const routes: Routes = [
  // ENTITY CHILD ROUTES
  
  
  // BEGIN ENTITIES FOR SERVICE: financeiro.contasreceber
  { path: 'contareceber', loadChildren: './modules/financeiro/contasreceber/contareceber/contareceber.module#ContaReceberModule' },
  // END ENTITIES FOR SERVICE: financeiro.contas_receber
  
  
  // *****
  { path: 'mainmenu', loadChildren: './modules/cadastros/fornecedor/fornecedor/fornecedor.module#FornecedorModule' },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  
  { path: 'login', component: LoginComponent },
  { path: 'confignewaccount', component: ConfigNewAccountComponent },
  { path: 'newaccount', component: NewAccountComponent },
  { path: 'confirmaccount', component: ConfirmAccountComponent }
];


@NgModule({

  imports: [
    RouterModule.forRoot(routes)
  ],

  exports: [
    RouterModule
  ]

})

export class AppRoutingModule { }

