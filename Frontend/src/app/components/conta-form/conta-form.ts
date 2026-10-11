import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ContaService } from '../../services/conta';
import { Conta } from '../../models/conta.model';

@Component({
  selector: 'app-conta-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './conta-form.html',
  styleUrl: './conta-form.css'
})
export class ContaForm implements OnInit {
  conta: Conta = { titular: '', saldo: 0 };
  isEdicao: boolean = false;

  constructor(
    private contaService: ContaService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdicao = true;
      this.contaService.buscarPorId(Number(id)).subscribe({
        next: (dados) => this.conta = dados,
        error: (erro) => alert('Erro: Não foi possível puxar os dados desta conta do Back-end!')
      });
    }
  }

  salvar(): void {
    
    if (!this.conta.titular || this.conta.titular.trim() === '') {
      alert('Por favor, preencha o nome do titular.');
      return;
    }

    if (this.isEdicao && this.conta.id) {
      //put
      this.contaService.atualizar(this.conta.id, this.conta).subscribe({
        next: () => {
          this.router.navigate(['/contas']);
        },
        error: (erro) => {
          console.error(erro);
          alert('ERRO NO PUT: O Java recusou a atualização! Verifique se o servidor está ligado.');
        }
      });
    } else {
      //post
      this.contaService.criar(this.conta).subscribe({
        next: () => {
          this.router.navigate(['/contas']); // Se der certo, volta à lista
        },
        error: (erro) => {
          console.error(erro);
          alert('ERRO NO POST: A ligação falhou! Faltou colocar o @CrossOrigin ou o @RequestBody no seu ContaController do Java?');
        }
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/contas']);
  }
}