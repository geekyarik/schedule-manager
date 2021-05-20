import { Component } from '@angular/core';
import { AuthService } from '../../../core/auth';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  constructor(private authService: AuthService) { }

  logout() {
    this.authService.logout();
  }

}
