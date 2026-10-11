import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ContaForm } from './conta-form';

describe('ContaForm', () => {
  let component: ContaForm;
  let fixture: ComponentFixture<ContaForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContaForm],
    }).compileComponents();

    fixture = TestBed.createComponent(ContaForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
