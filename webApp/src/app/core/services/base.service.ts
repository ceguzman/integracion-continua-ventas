import { Injectable } from '@angular/core';
import { catchError, map, Observable } from 'rxjs';
import {HttpClient} from '@angular/common/http'
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BaseService {

  constructor(private http:HttpClient) { }
  obtenerParametros(parametros?:any):Observable<any>{
    return this.http.get(environment.apiPokemon+'pokemon/',{
    // return this.http.get(environment.apiAgeOfEmpires+'civilizations',{
      // params:{...parametros},
    // headers:headers,
  })//.pipe(map(response => response),
    /// catchError(error => error)
  //)  
  }
  obtenerParametros2(parametros?:any):Observable<any>{
    return this.http.get('/api/v1/civilizations',{
    // return this.http.get(environment.apiAgeOfEmpires+'civilizations',{
      // params:{...parametros},
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
