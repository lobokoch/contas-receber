/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:26:04.789
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { TableModule } from 'primeng/table';
import { TooltipModule } from 'primeng/tooltip';
import { ToastModule } from 'primeng/toast';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { PanelModule } from 'primeng/panel';
import { InputSwitchModule } from 'primeng/inputswitch';
import { AccordionModule } from 'primeng/accordion';
import { SpinnerModule } from 'primeng/spinner';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import {CalendarModule} from 'primeng/calendar';
import { CurrencyMaskModule } from 'ng2-currency-mask';
import {CardModule} from 'primeng/card';

// Kerubin - BEGIN
import { FinanceiroContasReceberTranslationService } from './../i18n/./../i18n/financeiro-contasreceber-translation.service';
import { ContaReceberService } from './contareceber.service';
import { ContaReceberListComponent } from './list-contareceber.component';
import { ContaReceberComponent } from './crud-contareceber.component';
import { ContaReceberRoutingModule } from './contareceber-routing.module';
import { PlanoContaModule } from '../planoconta/planoconta.module';
import { ContaBancariaModule } from '../contabancaria/contabancaria.module';
import { CartaoCreditoModule } from '../cartaocredito/cartaocredito.module';
import { ClienteModule } from '../cliente/cliente.module';
// Kerubin - END

@NgModule({

  imports: [
    // PrimeNG
    CommonModule,
	FormsModule,
	InputTextModule,
	ButtonModule,
	InputTextareaModule,
	TableModule,
	TooltipModule,
	ToastModule,
	ConfirmDialogModule,
	AutoCompleteModule,
	PanelModule,
	InputSwitchModule,
	AccordionModule,
	SpinnerModule,
	DialogModule,
	DropdownModule,
	CalendarModule,
	CurrencyMaskModule,
	CardModule,

    // Kerubin
    ContaReceberRoutingModule,
	PlanoContaModule,
	ContaBancariaModule,
	CartaoCreditoModule,
	ClienteModule

  ],

  declarations: [
    ContaReceberComponent,
    ContaReceberListComponent
  ],

  exports: [

  ],

  providers: [
    ContaReceberService,
    FinanceiroContasReceberTranslationService
  ]

})

export class ContaReceberModule { }
