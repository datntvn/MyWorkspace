import { Component } from '@angular/core';

@Component({
  selector: 'app-server',
  templateUrl: './server.component.html',
  styleUrls: ['./server.component.css']
})
export class ServerComponent {
  // tslint:disable-next-line:no-inferrable-types
  serverId: number = 10;
  // tslint:disable-next-line:no-inferrable-types
  serverStatus: string = 'offline';

  getServerStatus(): string {
    return this.serverStatus;
  }
}
