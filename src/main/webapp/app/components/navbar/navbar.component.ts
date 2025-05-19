import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})
export class NavbarComponent {
  constructor(public auth: AuthService) {}

  hasRole(role: string): boolean {
    return this.auth.getRoles().includes(role);
  }

  logout() {
    this.auth.logout();
  }
}
