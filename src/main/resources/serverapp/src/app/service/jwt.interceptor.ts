import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { LocalStorageService } from './local-storage.service';

export const JwtInterceptor: HttpInterceptorFn = (req, next) => {
  const storage = inject(LocalStorageService);
  const jwtToken = storage.getItem("JWT_TOKEN");
    if (jwtToken) {
        req.clone({
            headers: req.headers.set('Authorization', `Bearer ${jwtToken}`),
        });
    }
  return next(req);
};