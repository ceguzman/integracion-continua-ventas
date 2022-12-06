import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  constructor(private http:HttpClient) { }


  obtenerProductos(parametros?:any):Observable<any>{
    return this.http.get(environment.apiVentas+'products/',{
      params:{...parametros},
    // headers:headers,
  }).pipe(map(response => response),
     catchError(error => error)
  )  
  }

  actualizar(body:any):Observable<any>{
    return this.http.post('urlApi',body,{
    // headers:headers,
  }).pipe(map(response => response),
     catchError(error => error)
  )  
  }  
}
