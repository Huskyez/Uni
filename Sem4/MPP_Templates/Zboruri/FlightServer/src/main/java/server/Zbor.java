/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package server;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)", date = "2019-05-08")
public class Zbor implements org.apache.thrift.TBase<Zbor, Zbor._Fields>, java.io.Serializable, Cloneable, Comparable<Zbor> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Zbor");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("ID", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField DESTINATIE_FIELD_DESC = new org.apache.thrift.protocol.TField("destinatie", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField PLECARE_FIELD_DESC = new org.apache.thrift.protocol.TField("plecare", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField AEROPORT_FIELD_DESC = new org.apache.thrift.protocol.TField("aeroport", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField LOCURI_FIELD_DESC = new org.apache.thrift.protocol.TField("locuri", org.apache.thrift.protocol.TType.I32, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ZborStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ZborTupleSchemeFactory();

  public int ID; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String destinatie; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String plecare; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String aeroport; // required
  public int locuri; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "ID"),
    DESTINATIE((short)2, "destinatie"),
    PLECARE((short)3, "plecare"),
    AEROPORT((short)4, "aeroport"),
    LOCURI((short)5, "locuri");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ID
          return ID;
        case 2: // DESTINATIE
          return DESTINATIE;
        case 3: // PLECARE
          return PLECARE;
        case 4: // AEROPORT
          return AEROPORT;
        case 5: // LOCURI
          return LOCURI;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __ID_ISSET_ID = 0;
  private static final int __LOCURI_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("ID", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.DESTINATIE, new org.apache.thrift.meta_data.FieldMetaData("destinatie", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PLECARE, new org.apache.thrift.meta_data.FieldMetaData("plecare", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.AEROPORT, new org.apache.thrift.meta_data.FieldMetaData("aeroport", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.LOCURI, new org.apache.thrift.meta_data.FieldMetaData("locuri", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Zbor.class, metaDataMap);
  }

  public Zbor() {
  }

  public Zbor(
    int ID,
    java.lang.String destinatie,
    java.lang.String plecare,
    java.lang.String aeroport,
    int locuri)
  {
    this();
    this.ID = ID;
    setIDIsSet(true);
    this.destinatie = destinatie;
    this.plecare = plecare;
    this.aeroport = aeroport;
    this.locuri = locuri;
    setLocuriIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Zbor(Zbor other) {
    __isset_bitfield = other.__isset_bitfield;
    this.ID = other.ID;
    if (other.isSetDestinatie()) {
      this.destinatie = other.destinatie;
    }
    if (other.isSetPlecare()) {
      this.plecare = other.plecare;
    }
    if (other.isSetAeroport()) {
      this.aeroport = other.aeroport;
    }
    this.locuri = other.locuri;
  }

  public Zbor deepCopy() {
    return new Zbor(this);
  }

  @Override
  public void clear() {
    setIDIsSet(false);
    this.ID = 0;
    this.destinatie = null;
    this.plecare = null;
    this.aeroport = null;
    setLocuriIsSet(false);
    this.locuri = 0;
  }

  public int getID() {
    return this.ID;
  }

  public Zbor setID(int ID) {
    this.ID = ID;
    setIDIsSet(true);
    return this;
  }

  public void unsetID() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field ID is set (has been assigned a value) and false otherwise */
  public boolean isSetID() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIDIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getDestinatie() {
    return this.destinatie;
  }

  public Zbor setDestinatie(@org.apache.thrift.annotation.Nullable java.lang.String destinatie) {
    this.destinatie = destinatie;
    return this;
  }

  public void unsetDestinatie() {
    this.destinatie = null;
  }

  /** Returns true if field destinatie is set (has been assigned a value) and false otherwise */
  public boolean isSetDestinatie() {
    return this.destinatie != null;
  }

  public void setDestinatieIsSet(boolean value) {
    if (!value) {
      this.destinatie = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getPlecare() {
    return this.plecare;
  }

  public Zbor setPlecare(@org.apache.thrift.annotation.Nullable java.lang.String plecare) {
    this.plecare = plecare;
    return this;
  }

  public void unsetPlecare() {
    this.plecare = null;
  }

  /** Returns true if field plecare is set (has been assigned a value) and false otherwise */
  public boolean isSetPlecare() {
    return this.plecare != null;
  }

  public void setPlecareIsSet(boolean value) {
    if (!value) {
      this.plecare = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getAeroport() {
    return this.aeroport;
  }

  public Zbor setAeroport(@org.apache.thrift.annotation.Nullable java.lang.String aeroport) {
    this.aeroport = aeroport;
    return this;
  }

  public void unsetAeroport() {
    this.aeroport = null;
  }

  /** Returns true if field aeroport is set (has been assigned a value) and false otherwise */
  public boolean isSetAeroport() {
    return this.aeroport != null;
  }

  public void setAeroportIsSet(boolean value) {
    if (!value) {
      this.aeroport = null;
    }
  }

  public int getLocuri() {
    return this.locuri;
  }

  public Zbor setLocuri(int locuri) {
    this.locuri = locuri;
    setLocuriIsSet(true);
    return this;
  }

  public void unsetLocuri() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __LOCURI_ISSET_ID);
  }

  /** Returns true if field locuri is set (has been assigned a value) and false otherwise */
  public boolean isSetLocuri() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __LOCURI_ISSET_ID);
  }

  public void setLocuriIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __LOCURI_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetID();
      } else {
        setID((java.lang.Integer)value);
      }
      break;

    case DESTINATIE:
      if (value == null) {
        unsetDestinatie();
      } else {
        setDestinatie((java.lang.String)value);
      }
      break;

    case PLECARE:
      if (value == null) {
        unsetPlecare();
      } else {
        setPlecare((java.lang.String)value);
      }
      break;

    case AEROPORT:
      if (value == null) {
        unsetAeroport();
      } else {
        setAeroport((java.lang.String)value);
      }
      break;

    case LOCURI:
      if (value == null) {
        unsetLocuri();
      } else {
        setLocuri((java.lang.Integer)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getID();

    case DESTINATIE:
      return getDestinatie();

    case PLECARE:
      return getPlecare();

    case AEROPORT:
      return getAeroport();

    case LOCURI:
      return getLocuri();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetID();
    case DESTINATIE:
      return isSetDestinatie();
    case PLECARE:
      return isSetPlecare();
    case AEROPORT:
      return isSetAeroport();
    case LOCURI:
      return isSetLocuri();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Zbor)
      return this.equals((Zbor)that);
    return false;
  }

  public boolean equals(Zbor that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_ID = true;
    boolean that_present_ID = true;
    if (this_present_ID || that_present_ID) {
      if (!(this_present_ID && that_present_ID))
        return false;
      if (this.ID != that.ID)
        return false;
    }

    boolean this_present_destinatie = true && this.isSetDestinatie();
    boolean that_present_destinatie = true && that.isSetDestinatie();
    if (this_present_destinatie || that_present_destinatie) {
      if (!(this_present_destinatie && that_present_destinatie))
        return false;
      if (!this.destinatie.equals(that.destinatie))
        return false;
    }

    boolean this_present_plecare = true && this.isSetPlecare();
    boolean that_present_plecare = true && that.isSetPlecare();
    if (this_present_plecare || that_present_plecare) {
      if (!(this_present_plecare && that_present_plecare))
        return false;
      if (!this.plecare.equals(that.plecare))
        return false;
    }

    boolean this_present_aeroport = true && this.isSetAeroport();
    boolean that_present_aeroport = true && that.isSetAeroport();
    if (this_present_aeroport || that_present_aeroport) {
      if (!(this_present_aeroport && that_present_aeroport))
        return false;
      if (!this.aeroport.equals(that.aeroport))
        return false;
    }

    boolean this_present_locuri = true;
    boolean that_present_locuri = true;
    if (this_present_locuri || that_present_locuri) {
      if (!(this_present_locuri && that_present_locuri))
        return false;
      if (this.locuri != that.locuri)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ID;

    hashCode = hashCode * 8191 + ((isSetDestinatie()) ? 131071 : 524287);
    if (isSetDestinatie())
      hashCode = hashCode * 8191 + destinatie.hashCode();

    hashCode = hashCode * 8191 + ((isSetPlecare()) ? 131071 : 524287);
    if (isSetPlecare())
      hashCode = hashCode * 8191 + plecare.hashCode();

    hashCode = hashCode * 8191 + ((isSetAeroport()) ? 131071 : 524287);
    if (isSetAeroport())
      hashCode = hashCode * 8191 + aeroport.hashCode();

    hashCode = hashCode * 8191 + locuri;

    return hashCode;
  }

  @Override
  public int compareTo(Zbor other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetID()).compareTo(other.isSetID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ID, other.ID);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDestinatie()).compareTo(other.isSetDestinatie());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDestinatie()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.destinatie, other.destinatie);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPlecare()).compareTo(other.isSetPlecare());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPlecare()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.plecare, other.plecare);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetAeroport()).compareTo(other.isSetAeroport());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAeroport()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.aeroport, other.aeroport);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetLocuri()).compareTo(other.isSetLocuri());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLocuri()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.locuri, other.locuri);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Zbor(");
    boolean first = true;

    sb.append("ID:");
    sb.append(this.ID);
    first = false;
    if (!first) sb.append(", ");
    sb.append("destinatie:");
    if (this.destinatie == null) {
      sb.append("null");
    } else {
      sb.append(this.destinatie);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("plecare:");
    if (this.plecare == null) {
      sb.append("null");
    } else {
      sb.append(this.plecare);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("aeroport:");
    if (this.aeroport == null) {
      sb.append("null");
    } else {
      sb.append(this.aeroport);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("locuri:");
    sb.append(this.locuri);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ZborStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ZborStandardScheme getScheme() {
      return new ZborStandardScheme();
    }
  }

  private static class ZborStandardScheme extends org.apache.thrift.scheme.StandardScheme<Zbor> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Zbor struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.ID = iprot.readI32();
              struct.setIDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // DESTINATIE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.destinatie = iprot.readString();
              struct.setDestinatieIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // PLECARE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.plecare = iprot.readString();
              struct.setPlecareIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // AEROPORT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.aeroport = iprot.readString();
              struct.setAeroportIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // LOCURI
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.locuri = iprot.readI32();
              struct.setLocuriIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Zbor struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI32(struct.ID);
      oprot.writeFieldEnd();
      if (struct.destinatie != null) {
        oprot.writeFieldBegin(DESTINATIE_FIELD_DESC);
        oprot.writeString(struct.destinatie);
        oprot.writeFieldEnd();
      }
      if (struct.plecare != null) {
        oprot.writeFieldBegin(PLECARE_FIELD_DESC);
        oprot.writeString(struct.plecare);
        oprot.writeFieldEnd();
      }
      if (struct.aeroport != null) {
        oprot.writeFieldBegin(AEROPORT_FIELD_DESC);
        oprot.writeString(struct.aeroport);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(LOCURI_FIELD_DESC);
      oprot.writeI32(struct.locuri);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ZborTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ZborTupleScheme getScheme() {
      return new ZborTupleScheme();
    }
  }

  private static class ZborTupleScheme extends org.apache.thrift.scheme.TupleScheme<Zbor> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Zbor struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetID()) {
        optionals.set(0);
      }
      if (struct.isSetDestinatie()) {
        optionals.set(1);
      }
      if (struct.isSetPlecare()) {
        optionals.set(2);
      }
      if (struct.isSetAeroport()) {
        optionals.set(3);
      }
      if (struct.isSetLocuri()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetID()) {
        oprot.writeI32(struct.ID);
      }
      if (struct.isSetDestinatie()) {
        oprot.writeString(struct.destinatie);
      }
      if (struct.isSetPlecare()) {
        oprot.writeString(struct.plecare);
      }
      if (struct.isSetAeroport()) {
        oprot.writeString(struct.aeroport);
      }
      if (struct.isSetLocuri()) {
        oprot.writeI32(struct.locuri);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Zbor struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.ID = iprot.readI32();
        struct.setIDIsSet(true);
      }
      if (incoming.get(1)) {
        struct.destinatie = iprot.readString();
        struct.setDestinatieIsSet(true);
      }
      if (incoming.get(2)) {
        struct.plecare = iprot.readString();
        struct.setPlecareIsSet(true);
      }
      if (incoming.get(3)) {
        struct.aeroport = iprot.readString();
        struct.setAeroportIsSet(true);
      }
      if (incoming.get(4)) {
        struct.locuri = iprot.readI32();
        struct.setLocuriIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

