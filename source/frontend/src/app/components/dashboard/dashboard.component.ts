import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  menuBtn: any;
  root: any;

  ngOnInit() {
    this.root = document.getElementsByTagName("app-dashboard")[0];
    this.menuBtn = document.getElementById("menu-btn");
    this.menuBtn.addEventListener("click", () => this.toggleSidebar())
  }

  toggleSidebar() {
    for (let icon of this.menuBtn.getElementsByTagName("i")) {
      if (icon.classList.value == "fa-solid fa-xmark hidden") {
        this.root.style.gridTemplateColumns = "250px 1fr";
      } else {
        this.root.style.gridTemplateColumns = "65px 1fr";
      }
      icon.classList.toggle("hidden");
    }
    document.getElementsByTagName("app-sidebar")[0].classList.toggle("active");
  }
}
