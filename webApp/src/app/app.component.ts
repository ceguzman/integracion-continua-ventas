import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { BaseService } from './core/services/base.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit,OnDestroy {

  unsubscribe$ = new Subject<void>()
  productos=[]

  constructor(private baseService:BaseService){}

  ngOnInit(): void {
     this.cargarParametros()
  }

  ngOnDestroy(): void {
   this.unsubscribe$.next()
   this.unsubscribe$.complete()    
  }

  cargarParametros(){
    this.baseService.obtenerParametros()
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe(
      (response)=>{this.productos = response.civilizations
        console.log('Response okr',response) 
  },
      (error)=>{console.log('Sali por el error',error)})
  }
}
