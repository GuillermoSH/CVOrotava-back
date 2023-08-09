import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss']
})
export class TopbarComponent {
  dropdown: any;
  profileIcon: any;

  constructor(private router: Router) {}
  
  ngOnInit() {
    this.dropdown = document.getElementById("profile-dropdown");
    this.profileIcon = document.getElementById("profile-icon");

    document.addEventListener('mouseup', (e) => {
      if(!this.dropdown.contains(e.target) && !this.profileIcon.contains(e.target)) {
        this.dropdown.classList.remove("active");
      } else {
        this.toggleDropdown();
      }
    })
  }

  toggleDropdown() {
    this.dropdown.classList.toggle("active");
  }

  logout() {
    this.router.navigate(['/login']);
  }
}
