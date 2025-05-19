import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationEnd, Router, RouterLink } from '@angular/router';
import { Subscription } from 'rxjs';
import { ErrorHandler } from 'app/common/error-handler.injectable';
import { StatutCreditService } from 'app/statut-credit/statut-credit.service';
import { StatutCreditDTO } from 'app/statut-credit/statut-credit.model';


@Component({
  selector: 'app-statut-credit-list',
  imports: [CommonModule, RouterLink],
  templateUrl: './statut-credit-list.component.html'})
export class StatutCreditListComponent implements OnInit, OnDestroy {

  statutCreditService = inject(StatutCreditService);
  errorHandler = inject(ErrorHandler);
  router = inject(Router);
  statutCredits?: StatutCreditDTO[];
  navigationSubscription?: Subscription;

  getMessage(key: string, details?: any) {
    const messages: Record<string, string> = {
      confirm: $localize`:@@delete.confirm:Do you really want to delete this element? This cannot be undone.`,
      deleted: $localize`:@@statutCredit.delete.success:Statut Credit was removed successfully.`    };
    return messages[key];
  }

  ngOnInit() {
    this.loadData();
    this.navigationSubscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.loadData();
      }
    });
  }

  ngOnDestroy() {
    this.navigationSubscription!.unsubscribe();
  }
  
  loadData() {
    this.statutCreditService.getAllStatutCredits()
        .subscribe({
          next: (data) => this.statutCredits = data,
          error: (error) => this.errorHandler.handleServerError(error.error)
        });
  }

  confirmDelete(id: number) {
    if (!confirm(this.getMessage('confirm'))) {
      return;
    }
    this.statutCreditService.deleteStatutCredit(id)
        .subscribe({
          next: () => this.router.navigate(['/statutCredits'], {
            state: {
              msgInfo: this.getMessage('deleted')
            }
          }),
          error: (error) => this.errorHandler.handleServerError(error.error)
        });
  }

}
