MAIN_CLASS := com.example.MyApplication

# Targets
.PHONY: clean run

all: clean install

clean:
	@echo "Cleaning up..."
	@mvn clean

install:
	@echo "Compiling and installing with Maven..."
	@mvn install	

run:
	@echo "Running the application..."
	@java -cp target/classes $(MAIN_CLASS)