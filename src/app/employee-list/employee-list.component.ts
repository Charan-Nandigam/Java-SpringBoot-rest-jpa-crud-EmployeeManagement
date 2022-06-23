import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees!: Observable<Employee[]>;
  constructor(private empService: EmployeeService, private router: Router) {
   }

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData() {
    this.employees = this.empService.getEmployeeList();
  } 

  deleteEmployee(id: number) {
    this.empService.deleteEmployee(id).subscribe(data => {
      console.log(data);
      this.reloadData();
    }, error => console.log(error));
  }

  updateEmployee(id : number) {
    this.router.navigate(['update', id]);
  }

  employeeDetails(id: number) {
    this.router.navigate(['details', id]);
  }
}
