import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  username = 'admin';
  password = '1234';
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {
  }

  login() {
    this.authService.login(this.username, this.password).subscribe({
      next: data => {
        this.authService.saveToken(data.accessToken);
        this.authService.saveRoles(data.roles);
        this.router.navigateByUrl('/dashboard');
      },
      error: err => this.errorMessage = 'Bad credentials'
    });
  }
}
