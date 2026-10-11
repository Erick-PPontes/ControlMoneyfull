import { Routes } from '@angular/router';
// Importamos com os nomes curtos agora:
import { ContaLista } from './components/conta-lista/conta-lista';
import { ContaForm } from './components/conta-form/conta-form';

export const routes: Routes = [
  { path: '', redirectTo: 'contas', pathMatch: 'full' },
  { path: 'contas', component: ContaLista },
  { path: 'contas/nova', component: ContaForm },
  { path: 'contas/editar/:id', component: ContaForm }
];