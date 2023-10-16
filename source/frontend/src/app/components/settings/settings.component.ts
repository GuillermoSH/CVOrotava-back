import { Component } from '@angular/core';
import { Configuration } from 'src/app/models/configuration.model';
import { ConfigurationService } from 'src/app/services/configuration.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})
export class SettingsComponent {
  configuration: Configuration = new Configuration();
  newConfiguration: Configuration = new Configuration();
  cathegoryYears: any;

  constructor(private configurationService: ConfigurationService) { }

  ngOnInit() {
    this.configurationService.getConfiguration().subscribe((configurations: Configuration[]) => {
      this.configuration = configurations[0]
      this.cathegoryYears = [
        {
          "cathegory": "Benjamín",
          "year": this.configuration.benjamin_year
        },
        {
          "cathegory": "Alevín",
          "year": this.configuration.alevin_year
        },
        {
          "cathegory": "Infantil",
          "year": this.configuration.infantil_year
        },
        {
          "cathegory": "Cadete",
          "year": this.configuration.cadete_year
        },
        {
          "cathegory": "Juvenil",
          "year": this.configuration.juvenil_year
        },
        {
          "cathegory": "Junior",
          "year": this.configuration.junior_year
        },
      ]
    })
  }

  test() {
    this.configurationService.getConfiguration().subscribe((configurations: Configuration[]) => this.configuration = configurations[0])
  }

  getConfigDate() {
    let modDate = this.configuration.mod_date;
    if (Number(modDate.split("-")[1]) >= 9) {
      return modDate.split("-")[2] + "/" + (Number(modDate.split("-")[2]) + 1)
    } else {
      return (Number(modDate.split("-")[2]) - 1) + "/" + modDate.split("-")[2];
    }
  }

  getCathegoryYears(initYear: string) {
    return initYear + " - " + (Number(initYear) - 1);
  }

  toggleUpdateCathegoryYearsModal() {
    document.getElementById("cathegory-years-modal")?.classList.toggle("hidden");
  }

  toggleUpdateNotificationEmailsModal() {
    document.getElementById("notification-emails-modal")?.classList.toggle("hidden");
  }
}
