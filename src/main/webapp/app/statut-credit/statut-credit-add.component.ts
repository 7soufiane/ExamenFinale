import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { ReactiveFormsModule, FormControl, FormGroup, Validators } from '@angular/forms';
import { InputRowComponent } from 'app/common/input-row/input-row.component';
import { StatutCreditService } from 'app/statut-credit/statut-credit.service';
import { StatutCreditDTO } from 'app/statut-credit/statut-credit.model';
import { ErrorHandler } from 'app/common/error-handler.injectable';


@Component({
  selector: 'app-statut-credit-add',
  imports: [CommonModule, RouterLink, ReactiveFormsModule, InputRowComponent],
  templateUrl: './statut-credit-add.component.html'
})
export class StatutCreditAddComponent {

  statutCreditService = inject(StatutCreditService);
  router = inject(Router);
  errorHandler = inject(ErrorHandler);

  addForm = new FormGroup({
    libelle: new FormControl(null, [Validators.required, Validators.maxLength(20)])
  }, { updateOn: 'submit' });

  getMessage(key: string, details?: any) {
    const messages: Record<string, string> = {
      created: $localize`:@@statutCredit.create.success:Statut Credit was created successfully.`
    };
    return messages[key];
  }

  handleSubmit() {
    window.scrollTo(0, 0);
    this.addForm.markAllAsTouched();
    if (!this.addForm.valid) {
      return;
    }
    const data = new StatutCreditDTO(this.addForm.value);
    this.statutCreditService.createStatutCredit(data)
        .subscribe({
          next: () => this.router.navigate(['/statutCredits'], {
            state: {
              msgSuccess: this.getMessage('created')
            }
          }),
          error: (error) => this.errorHandler.handleServerError(error.error, this.addForm, this.getMessage)
        });
  }

}
