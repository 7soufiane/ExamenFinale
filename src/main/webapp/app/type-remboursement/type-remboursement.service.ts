import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'environments/environment';
import { TypeRemboursementDTO } from 'app/type-remboursement/type-remboursement.model';


@Injectable({
  providedIn: 'root',
})
export class TypeRemboursementService {

  http = inject(HttpClient);
  resourcePath = environment.apiPath + '/api/typeRemboursements';

  getAllTypeRemboursements() {
    return this.http.get<TypeRemboursementDTO[]>(this.resourcePath);
  }

  getTypeRemboursement(id: number) {
    return this.http.get<TypeRemboursementDTO>(this.resourcePath + '/' + id);
  }

  createTypeRemboursement(typeRemboursementDTO: TypeRemboursementDTO) {
    return this.http.post<number>(this.resourcePath, typeRemboursementDTO);
  }

  updateTypeRemboursement(id: number, typeRemboursementDTO: TypeRemboursementDTO) {
    return this.http.put<number>(this.resourcePath + '/' + id, typeRemboursementDTO);
  }

  deleteTypeRemboursement(id: number) {
    return this.http.delete(this.resourcePath + '/' + id);
  }

}
