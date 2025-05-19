import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'environments/environment';
import { StatutCreditDTO } from 'app/statut-credit/statut-credit.model';


@Injectable({
  providedIn: 'root',
})
export class StatutCreditService {

  http = inject(HttpClient);
  resourcePath = environment.apiPath + '/api/statutCredits';

  getAllStatutCredits() {
    return this.http.get<StatutCreditDTO[]>(this.resourcePath);
  }

  getStatutCredit(id: number) {
    return this.http.get<StatutCreditDTO>(this.resourcePath + '/' + id);
  }

  createStatutCredit(statutCreditDTO: StatutCreditDTO) {
    return this.http.post<number>(this.resourcePath, statutCreditDTO);
  }

  updateStatutCredit(id: number, statutCreditDTO: StatutCreditDTO) {
    return this.http.put<number>(this.resourcePath + '/' + id, statutCreditDTO);
  }

  deleteStatutCredit(id: number) {
    return this.http.delete(this.resourcePath + '/' + id);
  }

}
