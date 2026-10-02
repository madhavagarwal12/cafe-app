# Bloom Design System

A design language derived from a botanical editorial poster: one flat saturated blue field, oversized ultra‑condensed cream type split across lines, a single photographic subject layered over the letters, and tiny uppercase caption columns pinned to the edges.

**Mood:** calm, editorial, gallery‑like, confident scale.
**Keywords:** flat field · giant type · layered subject · footnote captions · single accent.

---

## 1. Color

| Token | Hex | Role |
|---|---|---|
| `--blue-700` Deep Blue | `#355E86` | Dense‑text surfaces, footers |
| `--blue-600` Press Blue | `#3F6E9B` | Hover / pressed states |
| `--blue-500` **Field Blue** | `#4E7FAE` | **Primary background** |
| `--blue-400` Sky Blue | `#6A96C0` | Lifted surfaces |
| `--blue-200` Mist | `#B9CFE3` | Tints, illustrations |
| `--cream-100` Petal | `#F6F0E7` | Highest‑contrast text, highlights |
| `--cream-200` **Bone** | `#EDE2D3` | **Display type, labels, outlines** |
| `--cream-300` Linen | `#DCCDB8` | Muted cream, dividers on cream |
| `--pollen-500` **Pollen** | `#F0A41C` | **Accent — one focal point per view** |
| `--pollen-600` Amber | `#D9861A` | Accent pressed |
| `--stem-500` Stem | `#8C7A3C` | Thin organic lines, rare |
| `--ink-900` Ink | `#1F3247` | Text on cream surfaces |

**Usage ratio:** Blue 70% · Cream 24% · Pollen 4% · Stem 2%.

**Contrast**
- Bone on Field Blue ≈ 3.4:1 → only for display type, headings and labels ≥ 18px.
- Petal on Deep Blue ≈ 5.6:1 → small text on blue.
- Ink on Bone ≈ 11:1 → long‑form text on cream cards.

Semantic aliases:
```css
--bg: var(--blue-500);
--surface: var(--blue-400);
--text: var(--cream-200);
--text-strong: var(--cream-100);
--text-muted: rgba(237,226,211,.72);
--line: rgba(237,226,211,.35);
--accent: var(--pollen-500);
```

---

## 2. Typography

| Role | Font | Size | Line height | Case / tracking |
|---|---|---|---|---|
| Mega display | Six Caps (fallback Antonio, Oswald, Impact) | `clamp(9rem, 32vw, 26rem)` | 0.8 | UPPER, -0.01em |
| Display | Six Caps | `clamp(5rem, 16vw, 12rem)` | 0.8 | UPPER |
| H1 | Antonio 700 | `clamp(2.6rem, 6vw, 4.5rem)` | 0.95 | UPPER |
| H2 | Antonio 600 | 2rem | 1.05 | UPPER |
| H3 | Archivo 600 | 1.375rem | 1.2 | Sentence |
| Label / keyword | Archivo 500 | 1.25rem | 1.2 | UPPER, -0.01em |
| Body | Archivo 400 | 1rem | 1.5 | Sentence |
| Caption block | Archivo 400 | 11px | 1.3 | UPPER, max 18–20ch |
| Micro | Archivo 400 | 9px | 1.3 | UPPER |

Google Fonts:
```html
<link href="https://fonts.googleapis.com/css2?family=Six+Caps&family=Antonio:wght@400;600;700&family=Archivo:wght@400;500;600&display=swap" rel="stylesheet">
```

**Type rules**
- Display type is architecture, not text: set it so large it nearly fills the canvas width.
- Split one word across two lines; offset the second half (e.g. top‑left / bottom‑right).
- Captions are narrow ragged columns, never justified, never centred.
- Keyword triads (three single words) sit on one baseline, spread with `justify-content: space-between`.

---

## 3. Layout & Spacing

- **Grid:** 12 columns, 8px gutter (16px desktop).
- **Margins:** 16px mobile, 48px desktop.
- **Spacing scale (8pt):** 4 · 8 · 12 · 16 · 24 · 32 · 48 · 64 · 96 · 128.
- **Radius:** 0 everywhere; `999px` only for pills/tags.
- **Section rhythm:** 96px vertical padding, separated by a 1px `--line` rule.

**Signature composition (poster / hero)**
1. Flat `--bg` field.
2. Display word, first half, top‑left, full width.
3. Subject (single image/illustration) centred slightly left of axis, overlapping both type blocks, with `drop-shadow(0 18px 30px rgba(31,50,71,.28))`.
4. Display word, second half, bottom‑right — part of it sits *above* the subject at ~35–40% opacity (the "ghost" layer).
5. Caption column top‑right (right‑aligned) and middle‑left (left‑aligned).
6. Keyword triad along the lower third.
7. Micro colophon along the bottom margin.

Z‑order: `type (1) → subject (2) → ghost type (3) → captions (4)`.

---

## 4. Elevation & Effects

- UI is flat: no shadows, no gradients, no blur.
- Depth is reserved for the photographic subject only.
- Ghost layering: `opacity: .35–.4` on type placed above the subject.
- Motion: `cubic-bezier(.2,.7,.2,1)`, 250–300ms, colour and 1px translate only.

---

## 5. Components

**Buttons** — height 48px (small 36px), padding 0 24px, 1.5px Bone border, Archivo 600 13px UPPER, tracking 0.12em, square.
| Variant | Background | Text | Border | Hover |
|---|---|---|---|---|
| Primary | Bone | Field Blue | Bone | Petal |
| Outline | transparent | Bone | Bone | fill Bone, text Blue |
| Accent | Pollen | Ink | Pollen | Amber |
| Ghost | transparent | Bone, underlined (offset 6px) | none | Petal |
Focus: 2px Pollen outline, 3px offset. Disabled: 40% opacity.

**Tags** — 28px pill, 1px `--line` border, 11px Archivo 600 UPPER, tracking 0.1em. Optional 6px Pollen dot.

**Inputs** — underline only: transparent background, 1.5px Bone bottom border, 48px tall; label 11px UPPER muted above. Focus → Pollen underline.

**Cards** — square, 1px `--line` border, 24px padding, min height 340px. Huge display word + H4 (Antonio 600 24px UPPER) + caption. Variants: default (blue), cream (Bone bg, Blue display, Ink text), deep (Deep Blue bg). Hover → border becomes Bone.

**Navigation** — sticky, 56px, Field Blue background, bottom `--line` rule; brand in Antonio 700 UPPER; links 12px Archivo 500 UPPER, tracking 0.08em, muted → Petal on hover.

**Editorial frame** — 3 columns (160px · 1fr · 160px): left caption, centred display word + keyword triad, right caption (right‑aligned).

---

## 6. Imagery

- One subject per composition, cut out on the flat field.
- Natural, soft, slightly warm light; whites lean cream, not pure white.
- The subject may contain the only Pollen‑coloured element in view.
- Thin vertical organic lines (stems, stalks) in `--stem-500` may cross the full lower canvas.

---

## 7. Do / Don't

**Do**
- Run display type at 60–90% of the canvas width.
- Overlap subject and type; add a ghost fragment over the subject.
- Keep the background one flat hue.
- Use the yellow accent once per view.

**Don't**
- Use multiple images or busy backgrounds.
- Add rounded corners, shadows or gradients to UI.
- Justify or centre caption blocks.
- Put small body text in Bone on Field Blue.

---

## 8. CSS Tokens (copy‑paste)

```css
:root{
  --blue-700:#355E86; --blue-600:#3F6E9B; --blue-500:#4E7FAE; --blue-400:#6A96C0; --blue-200:#B9CFE3;
  --cream-100:#F6F0E7; --cream-200:#EDE2D3; --cream-300:#DCCDB8;
  --pollen-500:#F0A41C; --pollen-600:#D9861A; --stem-500:#8C7A3C; --ink-900:#1F3247;

  --font-display:"Six Caps","Antonio","Oswald",Impact,sans-serif;
  --font-head:"Antonio","Oswald","Arial Narrow",sans-serif;
  --font-body:"Archivo","Helvetica Neue",Arial,sans-serif;

  --fs-mega:clamp(9rem,32vw,26rem); --fs-display:clamp(5rem,16vw,12rem);
  --fs-h1:clamp(2.6rem,6vw,4.5rem); --fs-h2:2rem; --fs-h3:1.375rem;
  --fs-label:1.25rem; --fs-body:1rem; --fs-caption:.6875rem; --fs-micro:.5625rem;

  --s-1:4px; --s-2:8px; --s-3:12px; --s-4:16px; --s-5:24px; --s-6:32px;
  --s-7:48px; --s-8:64px; --s-9:96px; --s-10:128px;

  --radius:0; --radius-pill:999px;
  --shadow-subject:0 18px 30px rgba(31,50,71,.28);
  --ease:cubic-bezier(.2,.7,.2,1);
}
```
