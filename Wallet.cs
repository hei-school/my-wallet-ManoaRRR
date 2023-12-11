using System;

class Wallet
{
    private class Identification
    {
        public string Number { get; set; }
        public bool Status { get; set; } = false; // Lost: true, Not lost: false
    }

    private Identification CIN = new Identification();
    private decimal Balance = 0;
    private bool HaveDrivingLicense = false;
    private string BusinessCardContent = "";
    private string IDPhoto = null;
    private string CreditCardNumber = null;

    public void AddCIN(string number)
    {
        CIN.Number = number;
        CIN.Status = false;
        Console.WriteLine("CIN added successfully.");
    }

    public void RemoveCIN()
    {
        if (!CIN.Status)
        {
            CIN.Number = null;
            Console.WriteLine("CIN removed successfully.");
        }
        else
        {
            Console.WriteLine("Cannot remove lost CIN.");
        }
    }

    public void AddMoney(decimal amount)
    {
        Balance += amount;
        Console.WriteLine("Money added successfully.");
    }

    public void RemoveMoney(decimal amount)
    {
        if (Balance != 0 && amount <= Balance)
        {
            Balance -= amount;
            Console.WriteLine("Money removed successfully.");
        }
        else
        {
            Console.WriteLine("Insufficient funds or invalid amount.");
        }
    }

    public void CheckBalance()
    {
        Console.WriteLine("Current balance: " + Balance);
    }

    public void AddDrivingLicense()
    {
        HaveDrivingLicense = true;
        Console.WriteLine("Driving license added successfully.");
    }

    public void RemoveDrivingLicense()
    {
        if (HaveDrivingLicense)
        {
            HaveDrivingLicense = false;
            Console.WriteLine("Driving license removed successfully.");
        }
        else
        {
            Console.WriteLine("Driving license not found.");
        }
    }

    public void ShowBusinessCard()
    {
        Console.WriteLine("Business card content: " + BusinessCardContent);
    }

    public void ExportBusinessCardToPDF()
    {
        // Logic to export business card to PDF (not implemented here)
        Console.WriteLine("Business card exported to PDF.");
    }

    public void ShowIDPhoto()
    {
        Console.WriteLine("ID photo: " + IDPhoto);
    }

    public void UpdateIDPhoto(string photo)
    {
        IDPhoto = photo;
        Console.WriteLine("ID photo updated successfully.");
    }

    public void ShowCreditCardNumber()
    {
        Console.WriteLine("Credit card number: " + CreditCardNumber);
    }

    public void MakePayment()
    {
        // Logic to process payment (not implemented here)
        Console.WriteLine("Payment processed successfully.");
    }
}

class Program
{
    static void Main()
    {
        // Example Usage:
        Wallet myWallet = new Wallet();
        myWallet.AddCIN("123456789");
        myWallet.AddMoney(100);
        myWallet.CheckBalance();
        myWallet.RemoveMoney(50);
        myWallet.CheckBalance();
        myWallet.AddDrivingLicense();
        myWallet.ShowIDPhoto();
        myWallet.UpdateIDPhoto("new_photo.jpg");
        myWallet.ShowCreditCardNumber();
        myWallet.MakePayment();
    }
}

















