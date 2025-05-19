export class StatutCreditDTO {

  constructor(data:Partial<StatutCreditDTO>) {
    Object.assign(this, data);
  }

  id?: number|null;
  libelle?: string|null;

}
