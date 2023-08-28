import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  burguerBtn: any;
  xmarkBtn: any;

  ngOnInit() {
    this.burguerBtn = document.getElementById("burguer-btn");
    this.burguerBtn.addEventListener("click", () => this.toggleSidebar())
    this.xmarkBtn = document.getElementById("xmark-btn");
    this.xmarkBtn.addEventListener("click", () => this.toggleSidebar())
  }

  toggleSidebar() {
    this.xmarkBtn.classList.toggle("hiddenplus");
    this.burguerBtn.classList.toggle("hiddenplus");
    document.getElementById("sidebar-container")?.classList.toggle("md:w-[300px!important]");
    document.getElementById("header-logo-wrapper")?.classList.toggle("md:w-[300px!important]");
  }
}
