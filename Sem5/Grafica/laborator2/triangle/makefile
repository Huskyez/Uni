CXX = g++
LIBS = -lglfw -lGL -lGLEW 

IDIR =common
_DEPS = utils.h
DEPS = $(patsubst %,$(IDIR)/%,$(_DEPS))

ODIR=obj
_OBJ = utils.o
OBJ = $(patsubst %,$(ODIR)/%,$(_OBJ))

CXXFLAGS=-I$(IDIR) $(LIBS)

# ****************************************************
# dependencies of .o files on .cpp files
$(ODIR)/%.o: common/%.cpp $(DEPS)
	$(CXX) -c -o $@ $< $(CXXFLAGS)

main: $(OBJ)
	$(CXX) -o triangle main.cpp $^ $(CXXFLAGS)

clean: 
	rm -f $(ODIR)/*.o triangle

