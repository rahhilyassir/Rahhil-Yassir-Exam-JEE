import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (req.url.includes('/auth/')) {
      return next.handle(req);
    }
    let token = this.authService.getToken();
    if (token) {
      let authReq = req.clone({ setHeaders: { Authorization: 'Bearer ' + token } });
      return next.handle(authReq);
    }
    return next.handle(req);
  }
}
