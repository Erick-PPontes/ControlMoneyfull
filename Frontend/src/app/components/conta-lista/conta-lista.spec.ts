import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ContaLista } from './conta-lista';

describe('ContaLista', () => {
  let component: ContaLista;
  let fixture: ComponentFixture<ContaLista>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContaLista],
    }).compileComponents();

    fixture = TestBed.createComponent(ContaLista);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
