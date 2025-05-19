import { Component } from '@angular/core';

@Component({
  selector: 'app-admin',
  template: `
    <div class="container">
      <div class="card shadow p-4 mt-4">
        <h2 class="mb-3">Espace Admin</h2>
        <p class="text-muted">Section réservée à l'administrateur.</p>
      </div>
    </div>
  `
})
export class AdminComponent {}
