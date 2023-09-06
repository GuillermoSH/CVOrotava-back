import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-not-loaded',
  templateUrl: './not-loaded.component.html',
  styleUrls: ['./not-loaded.component.scss']
})
export class NotLoadedComponent {
  @Input() loaderErrorMsg: string = "";
  @Input() errorType: string = "";

  showModal() {
    document.getElementById("new-player-modal")?.classList.toggle("hidden");
  }  
}
