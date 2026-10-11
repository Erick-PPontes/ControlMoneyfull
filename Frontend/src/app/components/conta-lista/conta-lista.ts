import { Component, OnInit, ChangeDetectorRef } from '@angular/core'; 
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ContaService } from '../../services/conta';
import { Conta } from '../../models/conta.model';

@Component({
  selector: 'app-conta-lista',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './conta-lista.html',
  styleUrl: './conta-lista.css'
})
export class ContaLista implements OnInit {
  contas: Conta[] = [];

  constructor(
    private contaService: ContaService, 
    private router: Router,
    private cdr: ChangeDetectorRef 
  ) {}

  ngOnInit(): void {
    this.carregarContas();
  }

  carregarContas(): void {
    this.contaService.listarTodas().subscribe({
      next: (dados) => {
        this.contas = dados;
        this.cdr.detectChanges(); 
      },
      error: (erro) => {
        console.error("ERRO AO LER OS DADOS:", erro);
      }
    });
  }

  novaConta(): void {
    this.router.navigate(['/contas/nova']);
  }

  editarConta(id: number | undefined): void {
    if(id) {
      this.router.navigate(['/contas/editar', id]);
    }
  }

  deletarConta(id: number | undefined): void {
    if (id && confirm('Tem certeza que deseja apagar esta conta?')) {
      this.contaService.deletar(id).subscribe(() => {
        this.carregarContas();
      });
    }
  }
}