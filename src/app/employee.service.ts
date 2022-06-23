import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = 'http://localhost:8080/api/employees';
  constructor(private http : HttpClient) { } //Injecting http client

  createEmployee(employee : Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/newemp`, employee);
  }

  getEmployeeList() : Observable<any> {
    return this.http.get(`${this.baseUrl}/all`);
  }

  getEmployee(id : number) : Observable<any> {
    return this.http.get(`${this.baseUrl}/id/${id}`);
  }

  updateEmployee(id : number, value: any) : Observable<Object> {
    return this.http.put(`${this.baseUrl}/update/${id}`, value);
  } 

  deleteEmployee(id : number) : Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`, {responseType : 'text'});
  }

}
