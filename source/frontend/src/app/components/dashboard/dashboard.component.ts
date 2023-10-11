import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  burguerBtn: any;
  xmarkBtn: any;

  constructor(private router: Router, private userService: UserService) { }

  ngOnInit() {
    let user_log = window.localStorage.getItem("user_log");
    if (user_log) {
      this.userService.getUserByUsername(JSON.parse(user_log).username).subscribe({
        next: () => {
          this.burguerBtn = document.getElementById("burguer-btn");
          this.burguerBtn.addEventListener("click", () => this.toggleSidebar());
          this.xmarkBtn = document.getElementById("xmark-btn");
          this.xmarkBtn.addEventListener("click", () => this.toggleSidebar());
          Array.from(document.getElementsByClassName("option")).forEach((option) => {
            option.addEventListener("click", () => {
              if (document.getElementById("sidebar-container")?.classList.contains("active")) {
                this.toggleSidebar()
              }
            });
          })
        },
        error: () => {
          Swal.fire({
            icon: "warning",
            title: "Parece que no has iniciado sesión",
            color: "black",
            background: "#fef08a",
            iconColor: "#eab308",
            toast: true,
            showConfirmButton: false,
            timer: 2000,
            timerProgressBar: true,
            position: "top-end",
          })
          this.router.navigate(['/login']);
        }
      })
    } else {
      Swal.fire({
        icon: "warning",
        title: "Primero inicia sesión",
        color: "black",
        background: "#fef08a",
        iconColor: "#eab308",
        toast: true,
        showConfirmButton: false,
        timer: 2000,
        timerProgressBar: true,
        position: "top-end",
      })
      this.router.navigate(['/login']);
    }
  }

  toggleSidebar() {
    let sidebar = document.getElementById("sidebar-container");

    setTimeout(() => {
      this.xmarkBtn.classList.toggle("hiddenplus");
      this.burguerBtn.classList.toggle("hiddenplus");
    }, 150);
    sidebar?.classList.toggle("active");
    sidebar?.children[0].classList.toggle("active");
  }
}
