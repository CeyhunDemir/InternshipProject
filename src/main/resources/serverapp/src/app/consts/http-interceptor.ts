import { HttpHandlerFn, HttpInterceptorFn, HttpRequest } from "@angular/common/http";

export const authenticationInterceptor: HttpInterceptorFn = (req: HttpRequest<unknown>, next:
    HttpHandlerFn) => {
    const jwtToken = localStorage.getItem("JWT_TOKEN");
    if (jwtToken) {
        req.clone({
            headers: req.headers.set('Authorization', `Bearer ${jwtToken}`),
        });
    }
    

    return next(req);
};
