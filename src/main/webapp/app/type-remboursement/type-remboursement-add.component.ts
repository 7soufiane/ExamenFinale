import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { ReactiveFormsModule, FormControl, FormGroup, Validators } from '@angular/forms';
import { InputRowComponent } from 'app/common/input-row/input-row.component';
import { TypeRemboursementService } from 'app/type-remboursement/type-remboursement.service';
import { TypeRemboursementDTO } from 'app/type-remboursement/type-remboursement.model';
import { ErrorHandler } from 'app/common/error-handler.injectable';


@Component({
  selector: 'app-type-remboursement-add',
  imports: [CommonModule, RouterLink, ReactiveFormsModule, InputRowComponent],
  templateUrl: './type-remboursement-add.component.html'
})
export class TypeRemboursementAddComponent {

  typeRemboursementService = inject(TypeRemboursementService);
  router = inject(Router);
  errorHandler = inject(ErrorHandler);

  addForm = new FormGroup({
    libelle: new FormControl(null, [Validators.required, Validators.maxLength(20)])
  }, { updateOn: 'submit' });

  getMessage(key: string, details?: any) {
    const messages: Record<string, string> = {
      created: $localize`:@@typeRemboursement.create.success:Type Remboursement was created successfully.`
    };
    return messages[key];
  }

  handleSubmit() {
    window.scrollTo(0, 0);
    this.addForm.markAllAsTouched();
    if (!this.addForm.valid) {
      return;
    }
    const data = new TypeRemboursementDTO(this.addForm.value);
    this.typeRemboursementService.createTypeRemboursement(data)
        .subscribe({
          next: () => this.router.navigate(['/typeRemboursements'], {
            state: {
              msgSuccess: this.getMessage('created')
            }
          }),
          error: (error) => this.errorHandler.handleServerError(error.error, this.addForm, this.getMessage)
        });
  }

}
