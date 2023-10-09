import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  user: User = new User();

  constructor(private router: Router, private userService: UserService) {}

  back() {
    this.router.navigate(['']);;
  }

  checkForm() {
    this.userService.validateLogin(this.user).subscribe({
      next: () => {
        this.router.navigate(["/dashboard"]);
      },
      error: (error) => {
        Swal.fire({
          title: "Ha habido un error",
          icon: "error",
          text: error.error.message,
        })
      }
    })
  }
}
