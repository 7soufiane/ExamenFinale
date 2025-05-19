import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private tokenKey = 'jwt_token';

  constructor(private http: HttpClient, private router: Router) {}

  login(credentials: {username: string, password: string}) {
    // Appel à l'API backend pour obtenir le JWT
    return this.http.post<{token: string}>('/api/auth/login', credentials);
  }

  saveToken(token: string) {
    localStorage.setItem(this.tokenKey, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  logout() {
    localStorage.removeItem(this.tokenKey);
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  // Décoder le JWT pour extraire les rôles
  getRoles(): string[] {
    const token = this.getToken();
    if (!token) return [];
    const payload = JSON.parse(atob(token.split('.')[1]));
    return payload.roles || [];
  }
}
