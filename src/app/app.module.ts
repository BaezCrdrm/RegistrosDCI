import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { RegistrosDCIComponent } from './registros-dci/registros-dci.component';
import { VerificacionComponent } from './verificacion/verificacion.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrosDCIComponent,
    VerificacionComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
