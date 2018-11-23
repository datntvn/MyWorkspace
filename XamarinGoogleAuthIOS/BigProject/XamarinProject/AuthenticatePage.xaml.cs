using DatNTDataGenerator;
using DatNT.ViewModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using DatNTCommon;
// DAT - begin - platform specific lib
using DatNT.Services;
// DAT - end - platform specific lib

namespace DatNT
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class AuthenticatePage : ContentPage
	{

		public AuthenticatePage()
		{
			InitializeComponent();
			NavigationPage.SetHasNavigationBar(this, false); // Temporarily Enabled for ios

			SetUpData();
            PlatformGoogleAuthManager.Current.InitAuthConfiguration(); // DAT - initialize multiplatform code for google auth
        }

		private async void SetUpData()
		{
            // DATNT: truncated non related code
		}

        // 2018Nov22nd - DAT - BEGIN - add google auth
        private async void btnLgin_Clicked(object sender, EventArgs e)
        {
            Console.WriteLine("Begin test login");
            PlatformGoogleAuthManager.Current.Authenticate();

        }
        private async void btnShowToken_Clicked(object sender, EventArgs e)
        {
            var token = PlatformGoogleAuthManager.Current.GetToken();
            PlatformGoogleAuthManager.Current.RetrieveEmail();
            Console.WriteLine("token: " + token);
            lblToken.Text = token;
        }
        private async void btnShowEmail_Clicked(object sender, EventArgs e)
        {

            var email = PlatformGoogleAuthManager.Current.GetEmail();
            lblEmail.Text = email;
        }
        protected override void OnAppearing()
        {
            var accessToken = PlatformGoogleAuthManager.Current.GetToken();
            if (accessToken == null)
            {
                btnLogin.IsEnabled = true;
                btnShowToken.IsEnabled = false;
                btnShowEmail.IsEnabled = false;
            }
            else
            {
                btnLogin.IsEnabled = false;
                btnShowToken.IsEnabled = true;
                btnShowEmail.IsEnabled = true;
            }
        }
        // 2018Nov22nd - DAT - END - add google auth
    }
}