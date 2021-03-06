/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using System.IO;
using Thrift;
using Thrift.Collections;
using System.Runtime.Serialization;
using Thrift.Protocol;
using Thrift.Transport;

namespace CSharp
{

  #if !SILVERLIGHT
  [Serializable]
  #endif
  public partial class Angajat : TBase
  {
    private string _ID;
    private string _password;

    public string ID
    {
      get
      {
        return _ID;
      }
      set
      {
        __isset.ID = true;
        this._ID = value;
      }
    }

    public string Password
    {
      get
      {
        return _password;
      }
      set
      {
        __isset.password = true;
        this._password = value;
      }
    }


    public Isset __isset;
    #if !SILVERLIGHT
    [Serializable]
    #endif
    public struct Isset {
      public bool ID;
      public bool password;
    }

    public Angajat() {
    }

    public void Read (TProtocol iprot)
    {
      iprot.IncrementRecursionDepth();
      try
      {
        TField field;
        iprot.ReadStructBegin();
        while (true)
        {
          field = iprot.ReadFieldBegin();
          if (field.Type == TType.Stop) { 
            break;
          }
          switch (field.ID)
          {
            case 1:
              if (field.Type == TType.String) {
                ID = iprot.ReadString();
              } else { 
                TProtocolUtil.Skip(iprot, field.Type);
              }
              break;
            case 2:
              if (field.Type == TType.String) {
                Password = iprot.ReadString();
              } else { 
                TProtocolUtil.Skip(iprot, field.Type);
              }
              break;
            default: 
              TProtocolUtil.Skip(iprot, field.Type);
              break;
          }
          iprot.ReadFieldEnd();
        }
        iprot.ReadStructEnd();
      }
      finally
      {
        iprot.DecrementRecursionDepth();
      }
    }

    public void Write(TProtocol oprot) {
      oprot.IncrementRecursionDepth();
      try
      {
        TStruct struc = new TStruct("Angajat");
        oprot.WriteStructBegin(struc);
        TField field = new TField();
        if (ID != null && __isset.ID) {
          field.Name = "ID";
          field.Type = TType.String;
          field.ID = 1;
          oprot.WriteFieldBegin(field);
          oprot.WriteString(ID);
          oprot.WriteFieldEnd();
        }
        if (Password != null && __isset.password) {
          field.Name = "password";
          field.Type = TType.String;
          field.ID = 2;
          oprot.WriteFieldBegin(field);
          oprot.WriteString(Password);
          oprot.WriteFieldEnd();
        }
        oprot.WriteFieldStop();
        oprot.WriteStructEnd();
      }
      finally
      {
        oprot.DecrementRecursionDepth();
      }
    }

    public override string ToString() {
      StringBuilder __sb = new StringBuilder("Angajat(");
      bool __first = true;
      if (ID != null && __isset.ID) {
        if(!__first) { __sb.Append(", "); }
        __first = false;
        __sb.Append("ID: ");
        __sb.Append(ID);
      }
      if (Password != null && __isset.password) {
        if(!__first) { __sb.Append(", "); }
        __first = false;
        __sb.Append("Password: ");
        __sb.Append(Password);
      }
      __sb.Append(")");
      return __sb.ToString();
    }

  }

}
