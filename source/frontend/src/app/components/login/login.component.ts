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

  transformDisableHideBtns(
    transformBtn: any,
    hideBtn: any,
    disabledBtn: any = null,
    transformBtnColor: string
  ) {
    transformBtn?.classList.toggle('pointer-events-none');
    transformBtn?.children[0].classList.toggle('hiddenplus');
    transformBtn?.children[1].classList.toggle('hiddenplus');
    transformBtn?.children[2].classList.toggle('hiddenplus');
    transformBtn?.children[3].classList.toggle('hiddenplus');
    transformBtn?.classList.toggle(transformBtnColor);
    hideBtn?.classList.toggle('hidden');
    if (disabledBtn != null) {
      disabledBtn?.classList.toggle('disabled');
    }
  }

  checkForm() {
    let loginBtn = document.getElementById("login-btn");
    let cancelBtn = document.getElementById("cancel-login");
    let loginForm = document.getElementById("login-form");
    let notFoundError = document.getElementById("not-found-error");
    let notMatchError = document.getElementById("not-match-error");
    this.transformDisableHideBtns(loginBtn, cancelBtn, null, "bg-blue-700")
    this.userService.validateLogin(this.user).subscribe({
      next: (success) => {
        this.transformDisableHideBtns(loginBtn, cancelBtn, null, "bg-blue-700");
        notFoundError?.classList.add("hidden");
        notFoundError?.classList.add("hidden");
        window.localStorage.setItem("user_log", JSON.stringify({id: success.user_log.id, username: success.user_log.username}));
        this.router.navigate(["/dashboard"]);
      },
      error: (error) => {
        if (error.status == 404) {
          notFoundError?.classList.remove("hidden");
        } else {
          notMatchError?.classList.remove("hidden");
        }
        this.transformDisableHideBtns(loginBtn, cancelBtn, null, "bg-blue-700");
        setTimeout(()=>{
          notMatchError?.classList.add("hidden");
          notFoundError?.classList.add("hidden");
        }, 4000)
      }
    })
  }
}
