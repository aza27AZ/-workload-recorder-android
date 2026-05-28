from PIL import Image, ImageDraw

def create_icon(size):
    img = Image.new('RGB', (size, size), '#f5f5f7')
    draw = ImageDraw.Draw(img)
    
    scale = size / 192
    
    cx = size // 2
    cy = size // 2
    line_w = max(2, int(8 * scale))
    
    arm_len = int(34 * scale)
    
    # Horizontal
    draw.line([(cx - arm_len, cy), (cx + arm_len, cy)], fill='#0071e3', width=line_w)
    # Vertical
    draw.line([(cx, cy - arm_len), (cx, cy + arm_len)], fill='#0071e3', width=line_w)
    # Slash
    diag_off = int(24 * scale)
    diag_w = max(2, int(6 * scale))
    draw.line([(cx - diag_off, cy - diag_off), (cx + diag_off, cy + diag_off)], fill='#0071e3', width=diag_w)
    
    img.save(f'icon-{size}.png', 'PNG')
    print(f'Created icon-{size}.png')

# Standard Android/PWA icon sizes
create_icon(36)
create_icon(48)
create_icon(72)
create_icon(96)
create_icon(144)
create_icon(168)
create_icon(192)
create_icon(512)