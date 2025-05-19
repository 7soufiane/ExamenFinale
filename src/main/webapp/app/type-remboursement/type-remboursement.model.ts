export class TypeRemboursementDTO {

  constructor(data:Partial<TypeRemboursementDTO>) {
    Object.assign(this, data);
  }

  id?: number|null;
  libelle?: string|null;

}
