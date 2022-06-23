import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {

  id : number = 0;
  employee : Employee = new Employee();
  submitted = false;
  constructor(private route : ActivatedRoute, private router : Router, private empService: EmployeeService) { }

  ngOnInit(): void {
    this.employee = new Employee();
    this.id = this.route.snapshot.params['id'];
    this.empService.getEmployee(this.id).subscribe(data => {
      console.log(data);
      this.employee = data;
    }, error => console.log(error));
  }

  updateEmployee() {
    this.empService.updateEmployee(this.id, this.employee).subscribe(data => {
      console.log(data);
      this.employee = new Employee();
      this.gotoList();
    }, error => console.log(error));
  }

  gotoList() {
    this.router.navigate(['/employees']);
  }

  onSubmit() {
    this.submitted = true;
    this.updateEmployee();
  }
}
