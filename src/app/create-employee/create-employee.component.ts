import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {

  employee : Employee = new Employee();
  submitted = false;
  constructor(private empService : EmployeeService, private router : Router) { }

  ngOnInit(): void {
  }

  resetEmployee(): void {
    this.employee = new Employee();
    this.submitted = false;
  }

  save() {
    this.empService.createEmployee(this.employee).subscribe(data => {
      console.log(data);
      this.resetEmployee();
      this.gotoList();
    },
    error => console.log(error)
    );
  }
    onSubmit() {
      this.submitted = true;
      this.save();
    }
    gotoList() {
      this.router.navigate(['/employees']);
    }
}
