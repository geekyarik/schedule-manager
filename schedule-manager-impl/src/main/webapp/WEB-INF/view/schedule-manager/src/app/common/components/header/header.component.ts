import { Component } from '@angular/core';
import { AuthService } from '../../../core/auth';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  isAuthenticated$ = this.authService.isAuthenticated$;

  constructor(private authService: AuthService) { }

  toggleAuth() {
    this.isAuthenticated$.value ? this.logout() : this.authService.toLoginPage();
  }

  logout() {
    this.authService.logout();
  }

}
