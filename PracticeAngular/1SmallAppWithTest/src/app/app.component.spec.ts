import { TestBed, async } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { ApplicationHeaderComponent } from './application-header/application-header.component';
import { ApplicationContentComponent } from './application-content/application-content.component';
import { UserNotificationComponent } from './application-content/user-notification.component';
import { ApplicationFooterComponent } from './application-footer/application-footer.component';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        ApplicationHeaderComponent,
        ApplicationContentComponent,
        ApplicationFooterComponent,
        UserNotificationComponent
      ],
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'TheAnglCompany'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('TheAnglCompany');
  });

  // it('should render title in a h1 tag', () => {
  //   const fixture = TestBed.createComponent(AppComponent);
  //   fixture.detectChanges();
  //   const compiled = fixture.debugElement.nativeElement;
  //   expect(compiled.querySelector('h1').textContent).toContain('Welcome to TheAnglCompany clouddeveloper --> Dat!');
  // });
});
